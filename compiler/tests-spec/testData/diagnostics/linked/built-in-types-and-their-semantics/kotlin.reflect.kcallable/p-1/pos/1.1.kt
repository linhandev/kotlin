// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kcallable -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, resolving-callable-references -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.reflect.KCallable exposes name for property and function references
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1
class Holder {
    val prop: Int = 42
    fun fn(): String = "x"
}
fun case_1() {
    val p: KProperty1<Holder, Int> = Holder::prop
    checkSubtype<KCallable<*>>(p)
    p.name checkType { check<String>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: KFunction1<Holder, String> = Holder::fn
    checkSubtype<KCallable<*>>(f)
    f.name checkType { check<String>() }
}
