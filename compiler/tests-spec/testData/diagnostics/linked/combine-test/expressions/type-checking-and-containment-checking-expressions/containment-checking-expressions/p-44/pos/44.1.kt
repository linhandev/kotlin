// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 44 -> sentence 44
 *                declarations, function-declaration, extension-function-declaration -> paragraph 44 -> sentence 44
 *                operator-overloading, overview -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: lazy delegated receiver with extension contains and in infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val data: List<Int>)
operator fun Box.contains(x: Int): Boolean = x in data

class Wrapper(box: Box) {
    val inner by lazy { box }
}

fun case1(w: Wrapper): Boolean = 2 in w.inner

fun case2() {
    checkSubtype<Boolean>(case1(Wrapper(Box(listOf(1, 2, 3)))))
    checkSubtype<Boolean>(case1(Wrapper(Box(listOf(4, 5)))))
}
