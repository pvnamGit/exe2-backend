package com.motorbikes_hiring.service.dropboxService;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DropboxClient {
    private final String ACCESS_TOKEN = "grW4Z3mk2P8AAAAAAAAAARteeFq71F9bZvsEgmcH5JszBU1cAguiekc9zl0HqBB4";

    public DbxClientV2 dropboxClient() throws DbxException{
        DbxRequestConfig config = new DbxRequestConfig("dropbox/motorbike_go");
        DbxClientV2 client = new DbxClientV2(config,ACCESS_TOKEN);
        return client;
    }

}
