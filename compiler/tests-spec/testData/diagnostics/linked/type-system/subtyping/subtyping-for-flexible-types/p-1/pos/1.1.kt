// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-flexible-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Flexible platform types are subtypes of their lower and upper rigid bounds
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
    checkSubtype<String>(j)
    checkSubtype<String?>(j)
}

// TESTCASE NUMBER: 2
fun case_2() {
    val n = FlexJava.staticN
    checkSubtype<String?>(n)
    val x: String? = n
}

// TESTCASE NUMBER: 3
fun case_3() {
    val nn = FlexJava.staticNN
    checkSubtype<String>(nn)
    val x: String? = nn
}

// TESTCASE NUMBER: 4
fun case_4(j: String, n: String?, nn: String) {
    checkSubtype<String>(j)
    checkSubtype<String?>(n)
    checkSubtype<String>(nn)
}

// TESTCASE NUMBER: 5
fun case_5() {
    val j = FlexJava.staticJ
    val any: Any? = j
    checkSubtype<Any?>(any)
}
