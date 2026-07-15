// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-contexts-and-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Type context follows scope for inner, companion, local, enum, and object types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer6<T> {
    inner class Inner(val t: T)
}

fun case_1(): Outer6<Int>.Inner {
    val inner = Outer6<Int>().Inner(1)
    checkSubtype<Outer6<Int>.Inner>(inner)
    return inner
}


// TESTCASE NUMBER: 2
class WithCompanion7 {
    companion object Factory
}

fun case_2(): WithCompanion7.Factory {
    checkSubtype<WithCompanion7.Factory>(WithCompanion7.Factory)
    return WithCompanion7.Factory
}


// TESTCASE NUMBER: 3
fun case_3() {
    class Local9
    val x: Local9 = Local9()
    checkSubtype<Local9>(x)
}


// TESTCASE NUMBER: 4
enum class E10 { A, B }

fun case_4(): E10 {
    checkSubtype<E10>(E10.A)
    return E10.A
}


// TESTCASE NUMBER: 5
object Obj11

fun case_5(): Obj11 {
    checkSubtype<Obj11>(Obj11)
    return Obj11
}
