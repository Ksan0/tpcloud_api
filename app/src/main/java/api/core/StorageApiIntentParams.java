package api.core;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class StorageApiIntentParams implements Parcelable {

    public static final Creator<StorageApiIntentParams> CREATOR = new Creator<StorageApiIntentParams>() {
        @Override
        public StorageApiIntentParams createFromParcel(Parcel parcel) {
            StorageApiIntentParams intentParams = new StorageApiIntentParams();

            intentParams.methodName = parcel.readString();
            intentParams.params = (ArrayList<Object>) parcel.readValue(ArrayList.class.getClassLoader());

            return intentParams;
        }

        @Override
        public StorageApiIntentParams[] newArray(int i) {
            return null;
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(methodName);
        parcel.writeValue(params);
    }


    private String methodName = "";
    private ArrayList<Object> params = new ArrayList<Object>();

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setParams(Object params[]) {
        for (int i = 0; i < params.length; i++) {
            this.params.add(params[i]);
        }
    }

    public Object[] getParams() {
        return params.toArray();
    }

    public void Print() {
        Log.d("____IntentPrintMN", methodName);

        for (Object x: params) {
            Log.d("___IntentPrintP", String.format("val=%s, type=%s", x, x.getClass().getName()));
        }
    }
}
