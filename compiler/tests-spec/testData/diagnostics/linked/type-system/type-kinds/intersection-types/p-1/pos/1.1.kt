// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, intersection-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Intersection types arise from smart casts and common supertype inference
 * HELPERS: checkType
 */

interface IA {
    fun a(): Int
}

interface IB {
    fun b(): String
}

class C : IA, IB {
    override fun a() = 1
    override fun b() = "ok"
}

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    if (x is C) {
        checkSubtype<IA>(x)
        checkSubtype<IB>(x)
        checkSubtype<C>(x)
    }
}


// TESTCASE NUMBER: 2
open class Base
class Derived : Base(), Cloneable

fun case_2(x: Any) {
    if (x is Base && x is Cloneable) {
        checkSubtype<Base>(x)
        checkSubtype<Cloneable>(x)
    }
}


// TESTCASE NUMBER: 3
interface IC
enum class EA : IC { A }
enum class EB : IC { B }

fun case_3(a: Any) {
    val x = if (true) EA.A else EB.B
    checkSubtype<Enum<*>>(x)
    checkSubtype<IC>(x)
}
