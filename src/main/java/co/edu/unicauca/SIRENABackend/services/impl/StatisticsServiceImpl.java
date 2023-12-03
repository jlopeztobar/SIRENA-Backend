package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.models.StatisticsModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;
import co.edu.unicauca.SIRENABackend.repositories.IProgramRepository;
import co.edu.unicauca.SIRENABackend.services.StatisticsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private final IBookingRepository bookingRepository;
    @Autowired
    private final IProgramRepository programRepository;

    @Override
    public List<StatisticsModel> getClassroomsStatistics() {
        return getStatisticsBase(1);
    }

    @Override
    public List<StatisticsModel> getBuildingsStatistics() {
        return getStatisticsBase(3);
    }

    @Override
    public List<StatisticsModel> getFacultiesStatistics() {
        return getStatisticsBase(2);
    }

    @Override
    public List<StatisticsModel> getProgramsStatistics() {
        return getStatisticsBase(4);
    }

    @Override
    public List<ProgramModel> getProgramsFacultie(Integer facultieId) {
        List<ProgramModel> programsList = new ArrayList<>();
        for (ProgramModel program : programRepository.findAll()) {
            if (program.getFaculty().getId() == facultieId) {
                programsList.add(program);
            }
        }
        return programsList;
    }

    private List<StatisticsModel> getStatisticsBase(int type) {
        // 1 para salon, 2 para facultad, 3 para edificio
        List<StatisticsModel> statisticsResult = new ArrayList<>();
        List<BookingModel> bookingList = bookingRepository.findAll();
        for (BookingModel booking : bookingList) {
            // Verificar si ya fue guardado
            Integer indexFinded = switch (type) {
                case 1 -> findIndex(statisticsResult, booking.getClassroom().getName());
                case 2 -> findIndex(statisticsResult, booking.getFaculty().getName());
                case 3 -> findIndex(statisticsResult, booking.getClassroom().getBuilding().getName());
                case 4 -> findIndex(statisticsResult, booking.getProgram().getName());
                default -> -1;
            };
            if (indexFinded >= 0) {
                if(booking.getEstado().name().equals("Aceptada") || booking.getEstado().name().equals("Finalizada"))
                    statisticsResult.get(indexFinded).setReservas_aceptadas(statisticsResult.get(indexFinded).getReservas_aceptadas()+1);
                if(booking.getEstado().name().equals("Rechazada"))
                    statisticsResult.get(indexFinded).setReservas_rechazadas(statisticsResult.get(indexFinded).getReservas_rechazadas()+1);
                if(booking.getEstado().name().equals("Pendiente"))
                    statisticsResult.get(indexFinded).setReservas_pendientes(statisticsResult.get(indexFinded).getReservas_pendientes()+1);
            } else {
                StatisticsModel newStatistic = getStatisticsModel(type, booking);
                if(booking.getEstado().name().equals("Aceptada") || booking.getEstado().name().equals("Finalizada"))
                    newStatistic.setReservas_aceptadas(1);
                if(booking.getEstado().name().equals("Rechazada"))
                    newStatistic.setReservas_rechazadas(1);
                if(booking.getEstado().name().equals("Pendiente"))
                    newStatistic.setReservas_pendientes(1);
                statisticsResult.add(newStatistic);
            }
        }
        return statisticsResult;
    }

    private static StatisticsModel getStatisticsModel(int type, BookingModel booking) {
        StatisticsModel newStatistic = new StatisticsModel();
        switch (type) {
            case 1 -> newStatistic.setName(booking.getClassroom().getName());
            case 2 -> newStatistic.setName(booking.getFaculty().getName());
            case 3 -> newStatistic.setName(booking.getClassroom().getBuilding().getName());
            case 4 -> newStatistic.setName(booking.getProgram().getName());
        }
        return newStatistic;
    }

    private Integer findIndex(List<StatisticsModel> statistics, String name) {
        for (StatisticsModel statistic : statistics) {
            if (statistic.getName().equals(name))
                return statistics.indexOf(statistic);
        }
        return -1;
    }
}
