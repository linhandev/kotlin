// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 203 -> sentence 203
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 203 -> sentence 203
 *                inheritance, overriding -> paragraph 203 -> sentence 203
 * NUMBER: 1
 * DESCRIPTION: a single override in a class declaration resolves the same abstract member inherited from two interfaces; dispatch via either interface type yields the override
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

fun box(): String {
    if (ResolveBoth().f() != 1) return "NOK: resolve-both"
    val asA: AbstractA = ResolveBoth()
    if (asA.f() != 1) return "NOK: via-a"
    val asB: AbstractB = ResolveBoth()
    if (asB.f() != 1) return "NOK: via-b"

    if (SharedId().id() != "shared") return "NOK: shared-id"
    val asLeft: LeftId = SharedId()
    if (asLeft.id() != "shared") return "NOK: via-left"
    val asRight: RightId = SharedId()
    if (asRight.id() != "shared") return "NOK: via-right"

    if (PingBoth().ping() != 9) return "NOK: ping-both"
    val asPinger: Pinger = PingBoth()
    if (asPinger.ping() != 9) return "NOK: via-pinger"
    val asPonger: Ponger = PingBoth()
    if (asPonger.ping() != 9) return "NOK: via-ponger"
    return "OK"
}
