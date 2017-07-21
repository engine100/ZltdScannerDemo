package com.zltd.decoder;

public interface Constants {

    int DECODER_TYPE_HW = 1;
    int DECODER_TYPE_ZXING = 2;
    int DECODER_ZBAR = 3;
    int SINGLE_SHOOT_MODE = 0;
    int CONTINUOUS_SHOOT_MODE = 1;
    int HOLD_SHOOT_MODE = 2;
    int RETURN_SUCCESS = 0;
    int RETURN_SCANT_MODE_ERR = 1;
    int RETURN_CONN_STATE_ERR = 2;
    int RETURN_CAMERA_CONN_ERR = 3;
    int RETURN_GENERAL_ERR = 5;
    int TRANSFER_BY_API = 0;
    int TRANSFER_BY_EDITTEXT = 1;
    int TRANSFER_BY_KEY = 2;
    int FLASH_LIGHT = 1;
    int FLOOD_LIGHT = 2;
    int LOCATION_LIGHT = 3;
    int FLASH_AUTO_MODE = 0;
    int FLASH_ALWAYS_ON_MODE = 1;
    int FLASH_ALWAYS_OFF_MODE = 2;
    int AUTO_MODE = 0;
    int INDOOR_MODE = 1;
    int OUTDOOR_MODE = 2;
    int FULL_MODE = 0;
    int SAVING_MODE = 1;
    int GET_IMAGE_BYTES = 4096;


}
