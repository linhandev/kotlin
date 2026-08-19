// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 26 -> sentence 26
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: when branches can call abstract members implemented by enum constants
 */

// TESTCASE NUMBER: 1
enum class Op {
    A {
        override fun v() = 1
    },
    B {
        override fun v() = 2
    };
    abstract fun v(): Int
}

fun test(o: Op): Int = when (o) {
    Op.A -> o.v()
    Op.B -> o.v()
}

fun box(): String {
    if (test(Op.A) != 1) return "NOK"
    if (test(Op.B) != 2) return "NOK"
    return "OK"
}
