// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kproperty -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: overload-resolution, resolving-callable-references -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: property references are subtypes of kotlin.reflect.KProperty
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1
class Holder {
    val prop: Int = 42
}
fun case_1() {
    val typeProp: KProperty1<Holder, Int> = Holder::prop
    checkSubtype<KProperty<Int>>(typeProp)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val h = Holder()
    val boundProp: KProperty0<Int> = h::prop
    checkSubtype<KProperty<Int>>(boundProp)
}
