# Gemini Test

Sample application that uses LangChain4J to interface with OpenAI's ChatGPT LLM.

Based on https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/HelloWorldExample.java

## To Compile

```bash
./gradlew assemble
```

## To Run

You’ll need an API key.  So just follow the instructions to obtain your OpenAI
API key.

- [OpenAI ChatGPT](https://platform.openai.com/api-keys)
- [Anthropic Claude](https://console.anthropic.com/settings/keys)
- [Google Gemini](https://aistudio.google.com/app/apikey)

Save it to the `app/src/main/resources/.env` file as the corresponding
`..._API_KEY` environment variable.  You can use
`app/src/main/resources/.env.template` as a  guide to structure your `.env` 
file.

To run against OpenAI's ChatGPT:

```bash
./gradlew run --args="--model openai"
```

To run against Anthropic's Claude:

```bash
./gradlew run --args="--model anthropic"
```

To run against Google's Gemini:

```bash
./gradlew run --args="--model google"
```

## To Test

```bash
./gradlew check
```
