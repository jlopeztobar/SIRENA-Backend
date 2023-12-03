package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.models.StatisticsModel;

import java.util.List;

public interface StatisticsService {
    public List<StatisticsModel> getClassroomsStatistics();
    public List<StatisticsModel> getBuildingsStatistics();
    public List<StatisticsModel> getFacultiesStatistics();
    public List<StatisticsModel> getProgramsStatistics();
    public List<ProgramModel> getProgramsFacultie(Integer facultieId);
}
