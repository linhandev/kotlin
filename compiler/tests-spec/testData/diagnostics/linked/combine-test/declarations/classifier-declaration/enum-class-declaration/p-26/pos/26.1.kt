// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 26 -> sentence 26
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: when branches can call abstract members implemented by enum constants
 * HELPERS: checkType
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

fun case_1(o: Op) {
    checkSubtype<Int>(when (o) {
        Op.A -> o.v()
        Op.B -> o.v()
    })
}
