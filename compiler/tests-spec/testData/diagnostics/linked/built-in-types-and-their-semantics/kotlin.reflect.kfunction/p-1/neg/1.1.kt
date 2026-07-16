// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kfunction -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, resolving-callable-references -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.reflect.KFunction references require matching function signatures
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
import kotlin.reflect.KFunction0
class Box {
    fun greet(): String = "hi"
}
fun case_1() {
    val f: KFunction0<Int> = <!TYPE_MISMATCH!>Box::<!TYPE_MISMATCH!>greet<!><!>
}


// TESTCASE NUMBER: 2
fun case_2(box: Box) {
    val g: KFunction0<Int> = <!TYPE_MISMATCH!>box::<!TYPE_MISMATCH!>greet<!><!>
}
