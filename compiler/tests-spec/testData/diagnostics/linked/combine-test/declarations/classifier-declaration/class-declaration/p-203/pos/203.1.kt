// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 203 -> sentence 203
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 203 -> sentence 203
 *                inheritance, overriding -> paragraph 203 -> sentence 203
 * NUMBER: 1
 * DESCRIPTION: type inference when a single override in a class declaration resolves the same abstract member from two interfaces
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface AbstractA {
    fun f(): Int
}

interface AbstractB {
    fun f(): Int
}

class ResolveBoth : AbstractA, AbstractB {
    override fun f(): Int = 1
}

fun case1() {
    val c = ResolveBoth()
    c checkType { check<ResolveBoth>() }
    checkSubtype<AbstractA>(c)
    checkSubtype<AbstractB>(c)
    c.f() checkType { check<Int>() }
    val asA: AbstractA = c
    asA.f() checkType { check<Int>() }
    val asB: AbstractB = c
    asB.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface LeftId {
    fun id(): String
}

interface RightId {
    fun id(): String
}

class SharedId : LeftId, RightId {
    override fun id(): String = "shared"
}

fun case2() {
    val c = SharedId()
    c checkType { check<SharedId>() }
    checkSubtype<LeftId>(c)
    checkSubtype<RightId>(c)
    c.id() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface Pinger {
    fun ping(): Int
}

interface Ponger {
    fun ping(): Int
}

class PingBoth : Pinger, Ponger {
    override fun ping(): Int = 9
}

fun case3() {
    val c = PingBoth()
    c checkType { check<PingBoth>() }
    checkSubtype<Pinger>(c)
    checkSubtype<Ponger>(c)
    c.ping() checkType { check<Int>() }
}
