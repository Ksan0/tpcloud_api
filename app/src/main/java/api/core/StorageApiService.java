package api.core;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Method;

import api.usage.StorageApiFront;


public class StorageApiService extends IntentService {

    StorageApiBack storageApiBack;

    public StorageApiService() {
        super("StorageApiService");

        storageApiBack = new StorageApiBack();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        StorageApiIntentParams params = intent.getParcelableExtra(StorageApiFront.class.getName());
        Object objectParams[] = params.getParams();

        Class classParams[] = new Class[objectParams.length];
        for (int i = 0; i < objectParams.length; i++) {
            classParams[i] = objectParams[i].getClass();
        }

        Method method;
        try {
            method = storageApiBack.getClass().getMethod(params.getMethodName(), classParams);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        Object objectReturn[];
        try {
            objectReturn = (Object[]) method.invoke(storageApiBack, objectParams);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        StorageApiIntentParams paramsReturn = new StorageApiIntentParams();
        paramsReturn.setMethodName(params.getMethodName());
        if (objectReturn != null) {
            paramsReturn.setParams(objectReturn);
        }

        Intent answerIntent = new Intent(StorageApiBack.class.getCanonicalName());
        answerIntent.putExtra(StorageApiBack.class.getName(), paramsReturn);

        sendBroadcast(answerIntent);
    }
}
