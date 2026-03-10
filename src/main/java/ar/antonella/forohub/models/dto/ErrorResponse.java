package ar.antonella.forohub.models.dto;


import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(

        String status,
        String message,
        LocalDateTime time,
        List<String> details
) {

}
