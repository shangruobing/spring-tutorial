package com.infoweaver.springtutorial.dto;

import jakarta.validation.GroupSequence;

/**
 * @author Ruobing Shang 2023-10-16 13:17
 */
public class ValidatedGroup {
    public interface Retrieve {
    }

    public interface Create {
    }

    public interface Update {
    }

    public interface Delete {
    }

    @GroupSequence({Retrieve.class, Create.class, Update.class, Delete.class})
    public interface All {
    }
}
