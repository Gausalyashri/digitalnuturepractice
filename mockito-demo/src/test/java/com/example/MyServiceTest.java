package com.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class MyServiceTest {

    @Test
    public void testVerifyInteraction() {
        // 1. Create a mock object
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // 2. Call the method with specific arguments
        MyService service = new MyService(mockApi);
        service.fetchData();

        // 3. Verify the interaction
        verify(mockApi).getData();
    }
}
