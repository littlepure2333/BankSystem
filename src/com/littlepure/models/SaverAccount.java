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
     * Create a saver account.
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
     * Check if withdraw is allowed.
     * @return {@code true}/{@code false} yes or not
     */
    public boolean getWithdrawalIsAllowed() {
        if(haveApplyNotice()) {
            if(new Date().after(getNoticeDate())) {
                setWithdrawalIsAllowed(true);
            }
        }
        return this.withdrawalIsAllowed;
    }

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
     * Apply a notice, and feedback result.
     * @param noticeDays how many days after
     * @return {@code true}/{@code false} yes or not
     */
    public boolean applyNotice(int noticeDays) {
        if (noticeDays >= MINIMUM_PERIOD_OF_NOTICE) {
            // access present time
            Calendar now = Calendar.getInstance();
            // calculate noticeDate
            now.add(Calendar.DATE, noticeDays);
            setNoticeDate(now.getTime());
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * withdraw, and feedback result
     * @param amount amount of withdraw
     * @return 0 -success / 1 -exceed overdraft limit
     * / 2 -has not reach the day / 3 -has not apply a notice
     */
    @Override
    public int withdraw(double amount) {
        if(getWithdrawalIsAllowed()) {
            // default the state of notice
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
     * Check if a notice is applied
     * @return {@code true}/{@code false} yes or not
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
