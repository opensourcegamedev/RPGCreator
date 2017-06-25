package com.jukusoft.rpgcreator.engine.utils;

import com.jukusoft.rpgcreator.engine.exception.FilePermissionException;

import java.io.File;

/**
 * Created by Justin on 10.01.2017.
 */
public class FileCheckerUtils {

    private FileCheckerUtils() {
        //
    }

    /**
    * check, if directory exists, else create directory
     *
     * @param dir path to directory
    */
    public static void checkAndCreateReadableDir (File dir) throws FilePermissionException {
        //check, if dir is null
        if (dir == null) {
            throw new NullPointerException("dir cannot be null");
        }

        //create directory, if not exists
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //check file permissions
        if (!dir.canRead() || !dir.canWrite()) {
            //try to set file permissions
            if (!dir.setReadable(true)) {
                throw new FilePermissionException("Cannot set read permissions for directory '" + dir.getAbsolutePath() + "', please add read & write permissions manually.");
            }

            if (!dir.setWritable(true)) {
                throw new FilePermissionException("Cannot set write permissions for directory '" + dir.getAbsolutePath() + "', please add read & write permissions manually.");
            }

            if (!dir.canRead() || !dir.canWrite()) {
                throw new FilePermissionException("Directory " + dir.getAbsolutePath() + " has wrong file permissions, this directory has to be writable.");
            }
        }
    }

}
