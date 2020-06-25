
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

    public class testJoueur {


        @BeforeEach
        void setUp() throws Exception {
            // créer les instances ici
        }

        @ParameterizedTest
        @MethodSource("constructorProvider")
        void testOperationDS(int x, int y, int oraclePGCD) {
            //Test utilisant le stream argument ci-dessous
        }

        /**
         *
         */
        static Stream<Arguments> constructorProvider() {
            return Stream.of(
                    Arguments.of(-34,-39, 1),
                    Arguments.of(-42,90, 6),
                    Arguments.of(25,-33, 1),
                    Arguments.of(38,-30, 2),
                    Arguments.of(-76,9, 1),
                    Arguments.of(72,-21, 3),
                    Arguments.of(30,-2, 2),
                    Arguments.of(-35,-14, 7),
                    Arguments.of(5,32, 1),
                    Arguments.of(-78,58, 2),
                    Arguments.of(-42,-21, 21),
                    Arguments.of(52,-37, 1),
                    Arguments.of(108,-87, 3)
            );
        }


        /**
         * CT1((), (IllegalArgumentException))
         */
        @Test
        public void testDiviserException(){
            //Test unitaire
        }
}
