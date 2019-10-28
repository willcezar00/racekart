package org.william.racekart.repositories;

import java.util.List;

public interface FileRepository {

    public <TYPE> List<TYPE> read(String path, Class<TYPE> typeClass);

    public void write();

}
