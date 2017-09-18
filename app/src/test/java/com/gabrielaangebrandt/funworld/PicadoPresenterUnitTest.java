package com.gabrielaangebrandt.funworld;

import com.gabrielaangebrandt.funworld.picado_activity.PicadoContract;
import com.gabrielaangebrandt.funworld.picado_activity.presenter.PicadoPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PicadoPresenterUnitTest {

    PicadoContract.PicadoPresenter presenter;

    @Mock
    private PicadoContract.PicadoView view;

    @Before
    public void setUp() throws Exception {
        presenter = new PicadoPresenterImpl(view);
    }
/*
    @Test
    public void checkIfCityIsChoosen(){
        List<String> cityNames = Arrays.asList("Croatia","Hungary");
        verify(view).sendCityName(cityNames.get(0));
    }

    @Test
    public void onStartTest(){
        checkIfCityIsChoosen();
    }
*/
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}