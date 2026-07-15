// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, platform-types -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Nullable platform types cannot be used as non-nullable Kotlin types
 * HELPERS: checkType
 */

// FILE: Platform1.java

import org.jetbrains.annotations.*;

public class Platform1 {
    @NotNull
    public static Platform1 staticNN;
    @Nullable
    public static Platform1 staticN;
    public static Platform1 staticJ;
}

// FILE: cases.kt

// TESTCASE NUMBER: 1
fun case_1() {
    val n = Platform1.staticN
    checkSubtype<Platform1>(<!TYPE_MISMATCH!>n<!>)
    val y: Platform1 = <!TYPE_MISMATCH!>n<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val nn = Platform1.staticNN
    val x: Int = <!TYPE_MISMATCH!>nn<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val j = Platform1.staticJ
    val x: String = <!TYPE_MISMATCH!>j<!>
}


// TESTCASE NUMBER: 4
fun case_4(n: Platform1?) {
    val y: Platform1 = <!TYPE_MISMATCH!>n<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val nn = Platform1.staticNN
    checkSubtype<String>(<!TYPE_MISMATCH!>nn<!>)
}
