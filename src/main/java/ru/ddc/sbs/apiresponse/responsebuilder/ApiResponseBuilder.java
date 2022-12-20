package ru.ddc.sbs.apiresponse.responsebuilder;

import ru.ddc.sbs.apiresponse.ApiResponse;
import ru.ddc.sbs.apiresponse.ApiResponseCode;

public interface ApiResponseBuilder {
    ApiResponse buildErrorResponse(ApiResponseCode code, String message);
}
