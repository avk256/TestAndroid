package com.example.Lab3;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.SharedPreferences;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest extends TestCase {

    public String[] tags = new String[]{"Червоний:-65536", "Зелений:-144357", "Помаранчевий:-26368",
        "Жовтий:-598252", "Блакитний:-16728876", "Синій:-14401322", "Фіолетовий:-9422635",
        "Чорний:-16777216", "Рожевий:-841281027", };

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    public void testOnCreate() {
    }

    @Test
    public void testArray2dict() {
        Map map = MainActivity.array2dict(tags);
        int map_length = map.size();
        assertTrue(map_length == 9);
    }

    @Test
    public void testSetTestMode() {
        // Return the MockEditor when requesting it.
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putInt(anyString(), anyInt())).thenReturn(mockEditor);

        int status = 1;
        MainActivity.setTestMode(mockSharedPreferences, status);

        // check if editor.putInt() is called with parameters passed to setTestMode()
        verify(mockEditor).putInt("testing_mode", status);
        // check if editor.apply() is called
        verify(mockEditor).apply();
    }

    public void testOnYesClick() {
    }

    public void testOnNoClick() {
    }

    public void testChangeColors() {
    }

    public void testRandomChangeColors() {
    }

    public void testFixedChangeColors() {
    }
}