// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kproperty -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, resolving-callable-references -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.reflect.KProperty references require matching property signatures
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1
class Box {
    val n: Int = 0
}
fun case_1() {
    val p: KProperty1<Box, String> = <!TYPE_MISMATCH!>Box::<!TYPE_MISMATCH!>n<!><!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val c: KProperty0<Int> = Box()::n
    val w: KProperty0<String> = <!TYPE_MISMATCH!>c<!>
}
