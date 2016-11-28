package com.desafiolatam.desafioface.models;

import com.orm.SugarRecord;

/**
 * Created by QA on 22-11-2016.
 */

public class BaseSugar extends SugarRecord {
    public long server_id;

    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }

    public void create()
    {
        setServer_id(getId());
        setId(null);
        save();
    }

    @Override
    public long save() {
        return super.save();
    }
}
