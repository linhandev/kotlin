// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-callable-references-not-used-as-arguments-to-a-call -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: value receiver callable reference prefers instance member over companion member
 */

class Host1161 {
    fun pick1161(): Int = 1

    companion object {
        fun pick1161(): Int = 2
    }
}

// TESTCASE NUMBER: 1
fun case_1() {
    val host = Host1161()
    val ref: () -> Int = host::pick1161
    val ok = ref() == 1
}

// TESTCASE NUMBER: 2
fun case_2() {
    val ref: (Host1161) -> Int = Host1161::pick1161
    val ok = ref(Host1161()) == 1
}

// TESTCASE NUMBER: 3
fun case_3() {
    val ref: () -> Int = Host1161.Companion::pick1161
    val ok = ref() == 2
}
