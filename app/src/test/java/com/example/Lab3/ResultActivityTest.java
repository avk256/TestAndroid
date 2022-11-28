package com.example.Lab3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

import android.content.Context;
import android.content.Intent;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;

import java.util.List;

public class ResultActivityTest {

    @Mock
    Context mockContext;

    @Test
    public void onCreate() {
    }

    @Test
    public void addListenerOnButton() {
    }

    @Test
    public void testCreateIntent() {
        MockCharSequence mockCharSequence = new MockCharSequence("...Testing string...");

        try (MockedConstruction<Intent> mIntent = mockConstruction(Intent.class)) {

            MainActivity.createIntent(mockContext, mockCharSequence);

            // check if only one intent is created
            List<Intent> constructed = mIntent.constructed();
            assertEquals(constructed.size(), 1);

            // check if intent.putExtra() method with params passed to createIntent method is called
            verify(constructed.get(0)).putExtra("result", mockCharSequence);
        }
    }

    private static class MockCharSequence implements CharSequence {
        private char mText[];

        public MockCharSequence(String text) {
            mText = text.toCharArray();
        }

        public char charAt(int arg0) {
            if (arg0 >= 0 && arg0 < mText.length) {
                return mText[arg0];
            }
            throw new IndexOutOfBoundsException();
        }

        public int length() {
            return mText.length;
        }

        public CharSequence subSequence(int arg0, int arg1) {
            return null;
        }
    }
}