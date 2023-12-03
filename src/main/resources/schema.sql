SET GLOBAL event_scheduler = ON;

CREATE EVENT IF NOT EXISTS evt_auto_update_booking_state
ON SCHEDULE EVERY 30 MINUTE 
STARTS TIMESTAMP(CURRENT_DATE, MAKETIME(HOUR(NOW()) + 1, 0, 0))
DO 
    UPDATE booking 
    SET rsv_estado = 'Finalizada' 
    WHERE rsv_hora_fin < NOW() AND rsv_estado != 'Finalizada';
