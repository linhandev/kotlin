// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kfunction -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, resolving-callable-references -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function references are subtypes of kotlin.reflect.KFunction and kotlin.Function
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
class Holder {
    fun fn(x: Int): String = x.toString()
}
fun case_1() {
    val typeFn: KFunction2<Holder, Int, String> = Holder::fn
    checkSubtype<KFunction<String>>(typeFn)
    checkSubtype<(Holder, Int) -> String>(typeFn)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val boundFn: KFunction1<Int, String> = Holder()::fn
    checkSubtype<KFunction<String>>(boundFn)
    checkSubtype<(Int) -> String>(boundFn)
}
