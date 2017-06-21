package sample.Interfaces;

import com.sun.xml.internal.ws.api.pipe.PipelineAssembler;
import sample.Logic.Password;

import java.util.ArrayList;

/**
 * Created by maxhe on 21-6-2017.
 */
public interface IPasswordSQL {
    ArrayList<Password> passwordUser(String email);
}
