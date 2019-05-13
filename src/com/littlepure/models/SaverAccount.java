package com.littlepure.models;

import java.util.Calendar;
import java.util.Date;

public class SaverAccount extends BankAccount{
    public boolean withdrawalIsAllowed;
    public Date noticeDate;

    public static final int MINIMUM_PERIOD_OF_NOTICE = 3;
    public static final int WITHDRAWAL_IS_NOT_ALLOWED = 2;
    public static final int HAS_NOT_NOTICED = 3;

    public SaverAccount() {

    }

    /**
     * Create a saver account
     * @param name name of customer
     * @param address address of customer
     * @param DOB date of birth
     */
    public SaverAccount(String name, String address, String DOB) {
        super(name, address, DOB);
        this.limit = 0;
        this.withdrawalIsAllowed = false;
        this.noticeDate = null;
    }

    /**
     * 获取现在是否能允许withdraw
     * 调用时会自动更新允许状态并返回
     * @return true/false
     */
    public boolean getWithdrawalIsAllowed() {
        // 如果申请过notice
        if(haveApplyNotice()) {
            // 如果当前日期在noticeDate之后，允许取款
            if(new Date().after(getNoticeDate())) {
                setWithdrawalIsAllowed(true);
            }
        }
        return this.withdrawalIsAllowed;
    }

    /**
     * 设置NoticeDate之前一定要调用此函数
     * 如果已经有了NoticeDate
     * 就应该提示用户这个账户已经申请了notice
     * @return Date/null
     */
    public Date getNoticeDate() {
        return this.noticeDate;
    }

    private void setWithdrawalIsAllowed(boolean withdrawalIsAllowed) {
        this.withdrawalIsAllowed = withdrawalIsAllowed;
    }

    private void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    /**
     * 申请notice，总是会在现在没有NoticeDate时才会调用
     * 并检测申请日期是否符合要求
     * @param noticeDays -申请几天后notice
     * @return true/false
     */
    public boolean applyNotice(int noticeDays) {
        if (noticeDays >= MINIMUM_PERIOD_OF_NOTICE) {
            //获取当前时间
            Calendar now = Calendar.getInstance();
            //计算noticeDate
            now.add(Calendar.DATE, noticeDays);
            setNoticeDate(now.getTime());
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 取钱成功后会重置notice状态
     * 没到notice时间取不了钱
     * @param amount
     * @return 0 -成功 / 1 -超过limit / 2 -没到notice时间 / 3 -没申请notice
     */
    @Override
    public int withdraw(double amount) {
        if(getWithdrawalIsAllowed()) {
            //notice状态恢复成默认
            setNoticeDate(null);
            setWithdrawalIsAllowed(false);
            return super.withdraw(amount);
        }
        if(haveApplyNotice()){
            return WITHDRAWAL_IS_NOT_ALLOWED;
        }
        else {
            return HAS_NOT_NOTICED;
        }

    }

    /**
     * 判断账户有没有申请notice
     * @return true/dalse
     */
    public boolean haveApplyNotice() {
        if(noticeDate != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
