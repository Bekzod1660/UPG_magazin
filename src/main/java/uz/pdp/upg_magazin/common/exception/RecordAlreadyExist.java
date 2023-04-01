package uz.pdp.upg_magazin.common.exception;

public class RecordAlreadyExist extends RuntimeException{
    public RecordAlreadyExist(String message) {
        super(message);
    }
}
