// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Nullable assignment and safe operations preserve null safety
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: String?) {
    val y: String? = x
    checkSubtype<String?>(y)
}


// TESTCASE NUMBER: 2
fun case_2(x: String?) {
    val len: Int? = x?.length
    checkSubtype<Int?>(len)
}


// TESTCASE NUMBER: 3
fun case_3(x: String?) {
    val value: String = x ?: "default"
    checkSubtype<String>(value)
}


// TESTCASE NUMBER: 4
fun case_4(x: Int?) {
    val doubled: Int? = x?.let { it * 2 }
    checkSubtype<Int?>(doubled)
}


// TESTCASE NUMBER: 5
fun case_5(x: String?) {
    if (x != null) {
        checkSubtype<String>(x)
    }
}
