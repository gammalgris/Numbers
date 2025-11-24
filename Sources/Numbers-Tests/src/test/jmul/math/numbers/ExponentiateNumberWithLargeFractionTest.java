/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests exponentiating numbers.
 *
 * @author Kristian Kutin
 */
@Ignore
@UnitTest
@RunWith(Parameterized.class)
public class ExponentiateNumberWithLargeFractionTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * An exponent.
     */
    private final Fraction exponent;

    /**
     * The number of decimal places retained after cutting the fraction part
     */
    private final Number decimalPlaces;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     * @param expectedResult
     *        the expected result
     */
    public ExponentiateNumberWithLargeFractionTest(Number number, Fraction exponent, Number decimalPlaces,
                                                   Number expectedResult) {

        super();

        this.number = number;
        this.exponent = exponent;
        this.decimalPlaces = decimalPlaces;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        if (decimalPlaces == null) {

            return String.format("[%d] %s ^ [%d] %s -> [%d] %s default precision", number.base(), number,
                                 exponent.base(), exponent, expectedResult.base(), expectedResult);

        } else {

            return String.format("[%d] %s / [%d] %s -> [%d] %s precision [%d] %s", number.base(), number,
                                 exponent.base(), number, expectedResult.base(), expectedResult, decimalPlaces.base(),
                                 decimalPlaces);
        }
    }

    /**
     * Calculates the square root and checks the result.
     */
    @Test
    public void testExponentiation() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number.exponentiate(exponent);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = number.exponentiate(processingDetails, exponent);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Calculates the square root and checks the result.
     */
    @Test
    public void testExponentiationVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.exponentiate(number, exponent);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = Math.exponentiate(processingDetails, number, exponent);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "9876", "8765"), null,
                                      createNumber(10,
                                                   "426509898684333683981276517645282362171650330477891990381196458349213691925901059413791320845182186345700435586917415063781542846432800955018876606245500358999156277956022196974626184295451476013367813631875909775581383927201384866694380202251522749403502847168735837797987163835220861674612993269228840527395086292461364398451883931086127047664238944070976087535082320903945413567200525187714933660301278857655878104188581347744653603775931147867206540679256287944591523170768686592993248040042659818286411401085054719853906718554534939460801009956805869878812442916943765176043650513401563670279560252049988584769634930524255062940862745470586610649086861763464370678941650978977941439394537065714124983353917763019880019694813954572679676630917010217850227040688689437095162863303484720824117380653647553754171423176521600260248145880648511282704800636352915738369198279428189940438749527606389295939823356748565281084235637439613547699168334722363674083586259936948431408711674412892926509632981197392216534790641118271298677175743174663899209324945808766169112178379786416928723139175255640795606462333937009848158688741237002705351573052309854812362676887244090008332501047831765887332977285904908443939384369900116549532708602227709258155455796735056126628352223422258037299819399851076172223423154238382117127162347116667842394663522481377550128229347535449735277251301248434353797275136478934193686295072069867426144616401743884704869311574158490185290220298280488524958494708904701217567425004050112196420813923650215324332629147210132135281939828041798899946763338377554098750494675953877583738564492305424162051194398455012659652213638704021287157321133934483271604587670385416041483828817610659089341566369986336346977517258910348708867622198398459602589432775066255862935895126146816324537208806532700493143082804470399817172822660140451769865168391308419687305827625057642408613902020773633442598215200844840559624869393587485349613996494447795709203253094474595199973646233088854242402848085346199093441316970177011533055924291494791797800661317704416182356847080326296495408425210325679782902815434872896320490755159728345824196315113690496567153296144419492863940490785822901672648884047694001816705005775139308295561966908982335867348119264636219785695743318755086712842077411664607921824397754991751251257504393686093621037997973069071808706486958889550762782341472839927088844844833221807298322359594018772187266992210408244786151197229244808848484851720490036730780921018947114815920707841163263643308383355478726515072012814503420764123835690670138975793581789244254087304874891673517056484502439880108538258116486135553333344239869599056895588433418715013773421989521551839102918902848964861254865354235290279321124007806501710398654544343178577055977532925803763943731211960826268635395442180750384106461157317327537647251436674274346197667230071030978335230437549168468511526073646539903998821750920522766741132172413273676962638824894757927897813492929965078573879378552800222020702615388271836391247689700463639494947626602432295737670140559446957542175440427645552315095974455774654047540774098589608163751128216511607272651376256626651381356579445388688876709977200415779826095798950665202667524568669010145913312973414891290377133485871563144561454364548088239077757474627319200027504958672275239634326594854668732535959797929107090819542545183926193760595232768173656353808853356563393258806794097632660844053335567827486856674376347403090427015209744517358940204569274397287568938474102274076463086807315631853127790615790403550320684799839553438048638306477890939558231511493179477751198890281879872271080079636373720889977548783347571503840344847954826736366580216604014929658682385311427501543614247986463360962874290928962670293023910211772207123569055206465202709347022726619824936985605503997313485824224505448701241545539398950363078384039262455559060049596060699534238213219642637868175.1447280658") });
        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "9876", "8765"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "0"), createFraction(10, "9876", "8765"), null,
                                      createNumber(10, "0") });

        return parameters;
    }

}
