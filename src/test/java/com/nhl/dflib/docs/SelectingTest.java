package com.nhl.dflib.docs;

import com.nhl.dflib.*;
import com.nhl.dflib.series.IntSequenceSeries;
import org.junit.jupiter.api.Test;

import static com.nhl.dflib.Exp.*;

public class SelectingTest extends BaseTest {

    @Test
    public void selectAndReorderColumns() {

// tag::selectAndReorderColumns[]
        DataFrame df = DataFrame.foldByRow("first", "last", "middle").of(
                "Jerry", "Cosin", "M",
                "Juliana", "Walewski", null,
                "Joan", "O'Hara", "J");

        DataFrame df1 = df.selectColumns("last", "first");
// end::selectAndReorderColumns[]

        print("selectAndReorderColumns", df1);
    }

    @Test
    public void selectAndReorderColumns_ByIndex() {

// tag::selectAndReorderColumns_ByIndex[]
        DataFrame df = DataFrame.foldByRow("first", "last", "middle").of(
                "Jerry", "Cosin", "M",
                "Juliana", "Walewski", null,
                "Joan", "O'Hara", "J");

        DataFrame df1 = df.selectColumns(1, 0);
// end::selectAndReorderColumns_ByIndex[]

        print("selectAndReorderColumns_ByIndex", df1);
    }

    @Test
    public void selectRows() {

// tag::selectRows[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        DataFrame df1 = df.selectRows(2, 0, 0);
// end::selectRows[]

        print("selectRows", df1);
    }

    @Test
    public void selectRowsIndex() {

        // tag::selectRowsIndex[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        IntSeries rowNumbers = new IntSequenceSeries(0, df.height());

        IntSeries selectionIndex = rowNumbers.indexInt(i -> i % 3 == 0); // <1>

        DataFrame df1 = df.selectRows(selectionIndex);
        // end::selectRowsIndex[]

        print("selectRowsIndex", df1);
    }

    @Test
    public void selectByCondition() {

// tag::selectByCondition[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        DataFrame df1 = df.selectRows(and(
                $str("first").startsWith("J"),
                $str("last").startsWith("O")));
// end::selectByCondition[]

        print("filterByColumn", df1);
    }

    @Test
    public void filterByColumn() {

// tag::filterByColumn[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        DataFrame df1 = df.selectRows(
                "first",
                (String f) -> f != null && f.startsWith("J"));
// end::filterByColumn[]

        print("filterByColumn", df1);
    }

    @Test
    public void filterByRow() {

        // tag::filterByRow[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        DataFrame df1 = df.selectRows(r ->
                r.get("first").toString().startsWith("J")
                        && r.get("last").toString().startsWith("O"));
        // end::filterByRow[]

        print("filterByRow", df1);
    }

    @Test
    public void filterByBoolean() {

        // tag::filterByBoolean[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        Series<String> names = Series.of("Sandra", "Anton", "Joan");
        BooleanSeries mask = names.eq(df.getColumn("first")); // <1>

        DataFrame df1 = df.selectRows(mask);
        // end::filterByBoolean[]

        print("filterByBoolean", df1);
    }

    @Test
    public void headDataFrame() {

        // tag::headDataFrame[]
        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        DataFrame df1 = df.head(2); // <1>
        // end::headDataFrame[]

        print("headDataFrame", df1);
    }

    @Test
    public void tailDataFrame() {


        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        // tag::tailDataFrame[]
        DataFrame df1 = df.tail(1);
        // end::tailDataFrame[]

        print("tailDataFrame", df1);
    }

    @Test
    public void negativeHeadDataFrame() {

        DataFrame df = DataFrame.foldByRow("first", "last").of(
                "Jerry", "Cosin",
                "Juliana", "Walewski",
                "Joan", "O'Hara");

        // tag::negativeHeadDataFrame[]
        DataFrame df1 = df.head(-2);
        // end::negativeHeadDataFrame[]

        print("headDataFrame", df1);
    }
}
