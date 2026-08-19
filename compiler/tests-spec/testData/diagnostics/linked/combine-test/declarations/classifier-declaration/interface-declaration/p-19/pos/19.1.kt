// FIR_IDENTICAL
// LANGUAGE: +FunctionalInterfaceConversion
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 19 -> sentence 19
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: type inference for fun interface SAM member and inherited default function body
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Runner {
    fun run()
    fun label(): String = "run"
}

class PlainRunner : Runner {
    override fun run() {}
}

fun accept(r: Runner) {}

fun case1() {
    accept { }
    val r = PlainRunner()
    checkSubtype<PlainRunner>(r)
    checkSubtype<String>(r.label())
    checkSubtype<Runner>(r)
}
