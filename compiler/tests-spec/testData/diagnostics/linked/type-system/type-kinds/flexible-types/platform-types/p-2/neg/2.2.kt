// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, platform-types -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Platform type nullability violations produce TYPE_MISMATCH for rigid types
 * HELPERS: checkType
 */

// FILE: Platform2.java

import org.jetbrains.annotations.*;

public class Platform2 {
    @NotNull
    public static Integer staticNN;
    @Nullable
    public static Integer staticN;
    public static Integer staticJ;

    @NotNull
    public static String staticStringNN;
    @Nullable
    public static String staticStringN;
}

// FILE: cases.kt

// TESTCASE NUMBER: 1
fun case_1() {
    val n = Platform2.staticN
    checkSubtype<Int>(<!TYPE_MISMATCH!>n<!>)
    val y: Int = <!TYPE_MISMATCH!>n<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val s = Platform2.staticStringN
    checkSubtype<String>(<!TYPE_MISMATCH!>s<!>)
    val y: String = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val nn = Platform2.staticNN
    val x: String = <!TYPE_MISMATCH!>nn<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val s = Platform2.staticStringNN
    val x: Int = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 5
fun takeInt5(x: Int) {}

fun case_5() {
    takeInt5(<!TYPE_MISMATCH!>Platform2.staticN<!>)
}
