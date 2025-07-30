import pandas as pd
import matplotlib.pyplot as plt

df_mlb = pd.read_csv("mlb.csv")

df_mlb["wild_card_games_behind"] = df_mlb["wild_card_games_behind"].fillna(0)
df_mlb["games_behind"] = df_mlb["games_behind"].fillna(0)

df_mlb = df_mlb.dropna()

df_mlb["total_games"] = df_mlb["wins"] + df_mlb["losses"]

df_mlb["runs_per_game"] = df_mlb["runs_scored"] / df_mlb["total_games"]
df_mlb["runs_allowed_per_game"] = df_mlb["runs_allowed"] / df_mlb["total_games"]

df_mlb_xxi = df_mlb[df_mlb["year"]>=2000]

df_mlb_alltime = df_mlb.groupby("team_name").agg({
    "wins": "sum",                
    "losses": "sum",                
    "runs_scored": "sum",           
    "runs_allowed": "sum",          
    "winning_percentage": "mean",  
    "run_differential": "mean"     
}).reset_index()

plt.figure(figsize=(12, 8))

plt.subplot(2, 2, 1)
plt.hist(df_mlb["winning_percentage"], bins=20, color='skyblue', edgecolor='black')
plt.title("Distribuição da Winning Percentage")
plt.xlabel("Winning Percentage")
plt.ylabel("Frequência")


media = df_mlb["winning_percentage"].mean()
mediana = df_mlb["winning_percentage"].median()
plt.axvline(media, color='red', linestyle='--', label=f'Média: {media:.2f}')
plt.axvline(mediana, color='green', linestyle='-', label=f'Mediana: {mediana:.2f}')
plt.legend()


plt.subplot(2, 2, 2)
plt.scatter(df_mlb["runs_scored"], df_mlb["wins"], alpha=0.7, color='purple')
plt.title("Runs Scored vs Wins")
plt.xlabel("Runs Scored")
plt.ylabel("Wins")


top_teams_of_xxi = df_mlb_xxi["team_name"].value_counts().index[:5]
df_top = df_mlb[df_mlb["team_name"].isin(top_teams_of_xxi)]
data = [df_top[df_top["team_name"] == team]["run_differential"] for team in top_teams_of_xxi]
plt.subplot(2, 2, 3)
plt.boxplot(data, labels=top_teams_of_xxi)
plt.title("Run Differential dos Top 5 Times do seculo xxi")
plt.xlabel("Team Name")
plt.ylabel("Run Differential")


unique_teams = sorted(df_mlb["team_name"].unique())
print("\nTimes disponíveis:")
for i, team in enumerate(unique_teams):
    print(f"{i}: {team}")

team_index = int(input("\nDigite o número do time que deseja visualizar: "))
selected_team = unique_teams[team_index]

min_year = int(df_mlb["year"].min())
max_year = int(df_mlb["year"].max())
print(f"\nAnos disponíveis: {min_year} até {max_year}")
start_year = int(input("Ano inicial: "))
end_year = int(input("Ano final: "))
filtered_df = df_mlb[
    (df_mlb["team_name"] == selected_team) &
    (df_mlb["year"] >= start_year) &
    (df_mlb["year"] <= end_year)
]

plt.plot(filtered_df["year"], filtered_df["winning_percentage"], marker="o")
plt.title(f"Winning % do {selected_team} ({start_year}-{end_year})")
plt.xlabel("Ano")
plt.ylabel("Winning Percentage")
plt.grid(True)

plt.tight_layout()
plt.show()
