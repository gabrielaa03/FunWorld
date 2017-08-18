package com.gabrielaangebrandt.funworld.picado_activity.presenter;

import com.gabrielaangebrandt.funworld.picado_activity.PicadoContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Plava tvornica on 18.8.2017..
 */
public class PicadoPresenterImplTest {

    @Mock
    PicadoContract.PicadoView view;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void chooseCityTest() {
        PicadoPresenterImpl presenter = new PicadoPresenterImpl(view);
        presenter.chooseCity(15);
        verify(view).sendCityName("Nicosia");
    }
}
