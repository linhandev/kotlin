// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-flexible-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Nullable flexible type cannot substitute for non-null rigid type without check
 * HELPERS: checkType
 */

// FILE: FlexJava.java

import org.jetbrains.annotations.*;

public class FlexJava {
    @NotNull
    public static String staticNN;
    @Nullable
    public static String staticN;
    public static String staticJ;
}

// FILE: cases.kt

// TESTCASE NUMBER: 1
fun case_1() {
    val n = FlexJava.staticN
    val s: String = <!TYPE_MISMATCH!>n<!>
}

// TESTCASE NUMBER: 2
fun case_2(n: String?) {
    val s: String = <!TYPE_MISMATCH!>n<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val n = FlexJava.staticN
    checkSubtype<String>(<!TYPE_MISMATCH!>n<!>)
}
