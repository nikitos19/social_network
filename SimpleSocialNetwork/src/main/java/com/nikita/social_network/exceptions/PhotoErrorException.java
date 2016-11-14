package com.nikita.social_network.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nikita on 09.11.2016.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhotoErrorException extends RuntimeException {
}
