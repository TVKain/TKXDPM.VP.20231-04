package com.itep.hust.aimsgroup.controller.manager.validator;

import com.itep.hust.aimsgroup.model.media.Media;

public class MediaValidator {
    private Media media;
    public MediaValidator(Media media) {
        this.media = media;
    }

    public boolean checkInforInputMedia(Media media) {
//        check thông tin chung trước
//        Kiểm tra media loại nào -> gọi đến validator của loại media đó
        return true;
    }

}
