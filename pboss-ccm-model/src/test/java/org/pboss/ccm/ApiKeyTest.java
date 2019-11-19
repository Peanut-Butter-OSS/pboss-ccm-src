package org.pboss.ccm;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ApiKeyTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ApiKeyTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ApiKeyTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApiKey()
    {
        assertTrue( true );
    }

}
