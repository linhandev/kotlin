// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: type inference for empty class implementing interface that has only default function bodies
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Marker {
    fun ping(): String = "ok"
    fun tag(): String = "marker"
}

class EmptyImpl : Marker

fun case1() {
    val m = EmptyImpl()
    checkSubtype<EmptyImpl>(m)
    checkSubtype<Marker>(m)
    checkSubtype<String>(m.ping())
    checkSubtype<String>(m.tag())
}
