package com.baimao.client.net;

public class SimpleResponse<Succeed, Failed> {

    public static <Succeed, Failed> Builder<Succeed, Failed> newBuilder() {
        return new Builder<>();
    }

    private final int responseCode;
    private final Succeed mSucceed;
    private final Failed mFailed;

    private SimpleResponse(Builder<Succeed, Failed> builder) {
        this.responseCode = builder.responseCode;
        this.mSucceed = builder.mSucceed;
        this.mFailed = builder.mFailed;
    }


    public int code() {
        return responseCode;
    }

    public boolean isSucceed() {
        return mFailed == null || mSucceed != null;
    }

    public Succeed succeed() {
        return mSucceed;
    }


    public Failed failed() {
        return mFailed;
    }


    public static final class Builder<Succeed, Failed> {

        private int responseCode;
        private Failed mFailed;
        private Succeed mSucceed;

        public Builder<Succeed, Failed> code(int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder<Succeed, Failed> succeed(Succeed succeed) {
            this.mSucceed = succeed;
            return this;
        }

        public Builder<Succeed, Failed> failed(Failed failed) {
            this.mFailed = failed;
            return this;
        }

        public SimpleResponse<Succeed, Failed> build() {
            return new SimpleResponse<>(this);
        }

    }

}
