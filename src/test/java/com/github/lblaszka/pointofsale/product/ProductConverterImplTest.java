package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;

@RunWith( MockitoJUnitRunner.class )
public class ProductConverterImplTest
{
    private ProductConverter productConverter;

    @Before
    public void setUp()
    {
        productConverter = new ProductConverterImpl();
    }

    @Test
    public void convertDomainToDTO()
    {
        //Get
        Long productId = new Long( 1 );
        String productLabel = "ProductLabel";
        String productBarCode = "BarCodeContainer";
        BigDecimal productPrice = new BigDecimal( 10 );
        Product product = new Product( productId, productLabel, productBarCode, productPrice );


        //When
        ProductDTO productDTO = productConverter.convert( product );

        //Then
        Assert.assertNotNull( productDTO );
        Assert.assertThat( productDTO.getLabel(), is( product.getLabel()));
        Assert.assertThat( productDTO.getBarCodeContainer().getBarCodeObject(), is( product.getBarCode()) );
        Assert.assertThat( productDTO.getPrice(), is(product.getPrice()) );
    }

    @Test
    public void convertDTOToDomain()
    {
        //Get
        String dtoLabel = "ProductLabel";
        String dtoBarCodeString = "BarCodeContainer";
        BarCodeContainer dtoBarCodeContainer = new BarCodeContainer( dtoBarCodeString );
        BigDecimal dtoPrice = new BigDecimal( 10 );
        ProductDTO productDTO = new ProductDTO( dtoLabel, dtoBarCodeContainer, dtoPrice );


        //When
        Product product = productConverter.convert( productDTO );

        //Then
        Assert.assertNotNull( product );
        Assert.assertThat( product.getId(), is( new Long(0 )) );
        Assert.assertThat( product.getLabel(), is( productDTO.getLabel()));
        Assert.assertThat( product.getBarCode(), is( productDTO.getBarCodeContainer().getBarCodeObject() ) );
        Assert.assertThat( product.getPrice(), is(productDTO.getPrice()) );
    }

    @Test( expected = ProductConverterException.class )
    public void  convertDomainToDTOFromNull()
    {
        //Get
        Product product = null;

        //When

        ProductDTO productDTO = productConverter.convert( product );

        //Then
        Assert.assertNull( productDTO );
    }

    @Test( expected = ProductConverterException.class )
    public void convertDTOToDomainFromNull()
    {
        //Get
        ProductDTO productDTO = null;

        //When

        Product product = productConverter.convert( productDTO );

        //Then
        Assert.assertNull( productDTO );
    }

    @Test( expected = ProductConverterException.class )
    public void convertDTOToDomainWhenBarCodeObjectIsWrongType()
    {
        //Get
        String dtoLabel = "ProductLabel";
        int dtoInvalidBarCode = 123;
        BarCodeContainer dtoBarCodeContainer = new BarCodeContainer( dtoInvalidBarCode );
        BigDecimal dtoPrice = new BigDecimal( 10 );
        ProductDTO productDTO = new ProductDTO( dtoLabel, dtoBarCodeContainer, dtoPrice );


        //When
        Product product = productConverter.convert( productDTO );

        //Then
        Assert.assertNull( product );
    }
}