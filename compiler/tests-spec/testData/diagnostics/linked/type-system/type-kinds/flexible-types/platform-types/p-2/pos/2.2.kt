// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, platform-types -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Platform types for numeric and String Java types support flexible nullability
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
    public static String staticStringJ;
}

// FILE: cases.kt

// TESTCASE NUMBER: 1
fun case_1() {
    val nn = Platform2.staticNN
    checkSubtype<Int>(nn)
    checkSubtype<Int?>(nn)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val n = Platform2.staticN
    checkSubtype<Int?>(n)
    val y: Int? = n
}


// TESTCASE NUMBER: 3
fun case_3() {
    val s = Platform2.staticStringNN
    checkSubtype<String>(s)
    checkSubtype<String?>(s)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val s = Platform2.staticStringN
    checkSubtype<String?>(s)
    val y: String? = s
}


// TESTCASE NUMBER: 5
fun case_5() {
    val j = Platform2.staticJ
    checkSubtype<Int>(j)
    checkSubtype<Int?>(j)
}
