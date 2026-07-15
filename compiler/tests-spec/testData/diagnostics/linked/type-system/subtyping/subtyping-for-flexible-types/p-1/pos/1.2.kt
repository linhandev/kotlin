// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-flexible-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Flexible types may substitute for rigid types within (L..U) bounds
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
    val j = FlexJava.staticJ
    val s: CharSequence = j
    checkSubtype<CharSequence>(s)
}

// TESTCASE NUMBER: 2
fun case_2() {
    val j = FlexJava.staticJ
    val a: Any = j
    checkSubtype<Any>(a)
}

// TESTCASE NUMBER: 3
fun case_3() {
    val nn = FlexJava.staticNN
    val cs: CharSequence = nn
    checkSubtype<CharSequence>(cs)
}

// TESTCASE NUMBER: 4
fun case_4() {
    val n = FlexJava.staticN
    val cs: CharSequence? = n
    checkSubtype<CharSequence?>(cs)
}

// TESTCASE NUMBER: 5
fun case_5() {
    val j = FlexJava.staticJ
    checkSubtype<Comparable<String>>(j)
}
