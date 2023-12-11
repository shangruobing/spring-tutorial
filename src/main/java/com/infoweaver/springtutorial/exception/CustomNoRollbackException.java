package com.infoweaver.springtutorial.exception;

/**
 * 用于自定义事务的局部回滚，这个异常类不触发@Transactional的回滚
 * 使用 @Transactional(rollbackFor = Exception.class, noRollbackFor = CustomNoRollbackException.class)
 * 从而避免使用
 * Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
 * TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
 * @author Ruobing Shang 2023-11-10 12:25
 */
public class CustomNoRollbackException extends RuntimeException {
    public CustomNoRollbackException(String message) {
        super(message);
    }
}
