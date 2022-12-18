package ru.ddc.sbs.apiresponse.responsebuilder;

import org.springframework.stereotype.Component;
import ru.ddc.sbs.apiresponse.ApiResponse;
import ru.ddc.sbs.apiresponse.ApiResponseCode;

@Component
public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    @Override
    public ApiResponse buildErrorResponse(ApiResponseCode code, String message) {
        return new ApiResponse(code.getCode(), message);
    }
}
