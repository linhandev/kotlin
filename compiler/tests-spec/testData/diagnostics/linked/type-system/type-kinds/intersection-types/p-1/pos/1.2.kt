// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, intersection-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, upper-and-lower-bounds, greatest-lower-bound -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Denotable intersection types from classes implementing multiple interfaces
 * HELPERS: checkType
 */

interface I1
interface I2

class Impl : I1, I2

// TESTCASE NUMBER: 1
fun case_1(x: Impl) {
    checkSubtype<I1>(x)
    checkSubtype<I2>(x)
}


// TESTCASE NUMBER: 2
interface I3 {
    val p: Int
}

interface I4 {
    val q: String
}

class Impl2 : I3, I4 {
    override val p = 1
    override val q = "x"
}

fun case_2(x: Impl2) {
    checkSubtype<I3>(x)
    checkSubtype<I4>(x)
    checkSubtype<Int>(x.p)
    checkSubtype<String>(x.q)
}


// TESTCASE NUMBER: 3
interface I5 { fun f(): Int }
interface I6 { fun g(): String }

class Impl3 : I5, I6 {
    override fun f() = 1
    override fun g() = "ok"
}

fun case_3(x: Impl3) {
    checkSubtype<I5>(x)
    checkSubtype<I6>(x)
}
